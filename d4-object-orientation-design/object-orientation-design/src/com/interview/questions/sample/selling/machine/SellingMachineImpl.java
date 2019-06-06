package com.interview.questions.sample.selling.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class  SellingMachineImpl implements SellingMachine {
	
	Inventory<Coin> cashInventory = new Inventory<Coin>();
	Inventory<Product> itemInventory = new Inventory<Product>();
	
	protected long totalSales;
    protected Product currentItem;
    protected long currentBalance=0; 


	public SellingMachineImpl() {
		initialize();
	}
 	
	public abstract void initialize();
		
 	
	@Override
	public void insertCoin(Coin coin) {
		// Insert coin to cashInventory
		currentBalance = currentBalance+coin.getDenomination();
		cashInventory.add(coin);
	}
	@Override
	public long getItemPrice(Product item) {
		// get current item price
		if (itemInventory.hasItem(item)) {
			this.currentItem = item;
			return this.currentItem.getPrice();
		}
		throw new SoldOutException(item.getName()+ " sold out, Please buy another item");
	}
	/**
	 * collect item and change
	 * conditions
	 * (1) check if full paid
	 * (2) check if sold out
	 * (3) check if not sufficient change
	 * all the conditions should be 
	 */
	@Override
	public Bucket<Product, List<Coin>> collectItemChange() {
		
		
		List<Coin> changes = new ArrayList<Coin>();
		try {
			if (isFullPaid()) {
				
				if (getChange(changes)) {
							
					if (hasItemInInventory(currentItem)) {  // currentItem inventory exists
						
						itemInventory.deduct(currentItem); 
				 
						
						this.totalSales += currentItem.getPrice();
					}
					
				}
			}
			
		} catch (NotSufficientChangeException e) {
			 System.out.println(e.getMessage());
			 return null;
		} catch (SoldOutException e1) {
			 System.out.println(e1.getMessage());
			 return null;
		 
		} catch (NotFullPaidException e2) {
			 System.out.println(e2.getMessage());
			 return null;
		}
		return new Bucket<Product, List<Coin>>(currentItem,changes);
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		itemInventory.clear();
		cashInventory.clear();
		currentItem=null;
		currentBalance = 0;
	}
	
	public int getTotalChange(List<Coin> changes) {
		int sum = 0;
		for (Coin s: changes) {
			sum+=s.getDenomination();
		}
		return sum;
	}
	
	protected boolean hasItemInInventory(Product item) throws SoldOutException {
		if (itemInventory.hasItem(item)) {			
			return true;
		}
		throw new SoldOutException(item.getName()+" sold out, please choose another one!");
	}
	@Override
	public void refund() {
		// Refund current item money
		List<Coin> refund = new ArrayList<Coin>();
		getChange(refund);
		for (Coin c: refund) {
			cashInventory.deduct(c);
		}
		currentBalance = 0;
		currentItem = null;
 	}


	/**
	 * Get change based on amount 
	 * @param amount
	 * @return
	 * @throws NotSufficientChangeException
	 */
	protected boolean getChange(List<Coin> changes) throws NotSufficientChangeException{
		
		long amount = currentBalance - currentItem.getPrice();
       
        if(amount > 0){
           
            long balance = amount;
            while(balance > 0){
            	if (balance >= Coin.DOLLAR.getDenomination() && cashInventory.hasItem(Coin.DOLLAR)) {
            		changes.add(Coin.DOLLAR);
            		balance -= Coin.DOLLAR.getDenomination();
            		continue;
            	} else if (balance >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER)) {
            		changes.add(Coin.QUARTER);
            		balance -= Coin.QUARTER.getDenomination();
            		continue;
                }else if(balance >= Coin.DIME.getDenomination() 
                                 && cashInventory.hasItem(Coin.DIME)) {
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getDenomination();
                    continue;
                   
                }else if(balance >= Coin.NICKLE.getDenomination() 
                                 && cashInventory.hasItem(Coin.NICKLE)) {
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getDenomination();
                    continue;
                   
                }else if(balance >= Coin.PENNY.getDenomination() 
                                 && cashInventory.hasItem(Coin.PENNY)) {
                    changes.add(Coin.PENNY);
                    balance = balance - Coin.PENNY.getDenomination();
                    continue;
                   
                }else{
                    throw new NotSufficientChangeException("NotSufficientChange,Please try another product");
                }
            }
        }
        
        return true;
    }
	 

	protected boolean isFullPaid() throws NotFullPaidException {
		if (currentBalance >= currentItem.getPrice()) {
			return true;
		}
		throw new NotFullPaidException(currentItem.getName()+" Not fully paid!");	
	}
}
