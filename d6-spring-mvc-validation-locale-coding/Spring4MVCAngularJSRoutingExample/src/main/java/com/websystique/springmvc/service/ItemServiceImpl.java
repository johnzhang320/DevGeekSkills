package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Computer;
import com.websystique.springmvc.model.Phone;
import com.websystique.springmvc.model.Printer;
import com.websystique.springmvc.model.State;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
	
	private static final AtomicLong counter = new AtomicLong();

	private static List<Computer> computers;
	
	private static List<Phone> phones;
	
	private static List<Printer> printers;
	
	
	static{
		computers= populateDummyComputers();
		phones= populateDummyPhones();
		printers= populateDummyPrinters();
		
	}
	
	public List findItemsByCategory(String category) {

		if(StringUtils.equalsIgnoreCase(category, "computers")){
				return computers;
		} else if(StringUtils.equalsIgnoreCase(category, "phones")){
			return phones;
		} else if(StringUtils.equalsIgnoreCase(category, "printers")){
			return printers;
		}
		return computers;
	}

	public Object findItemById(long id, String category) {
		if(category.equalsIgnoreCase("computers")){
			for(Computer computer : computers){
				if(computer.getId() == id){
					return computer;
				}
			}
		}else if(category.equalsIgnoreCase("phones")){
			for(Phone phone : phones){
				if(phone.getId() == id){
					return phone;
				}
			}
		} if(category.equalsIgnoreCase("printers")){
			for(Printer printer : printers){
				if(printer.getId() == id){
					return printer;
				}
			}
			
		}
		return null;
	}
	
	
	private static List<Computer> populateDummyComputers(){
		List<Computer> computers = new ArrayList<Computer>();
		
		computers.add(new Computer(counter.incrementAndGet(),"Lenovo", "T420", State.NEW, 500, 2.4, 8, 1000));
		computers.add(new Computer(counter.incrementAndGet(),"Lenovo", "T640", State.NEW, 2000, 3.6, 32, 2000));
		computers.add(new Computer(counter.incrementAndGet(),"Apple", "IMAC21.5", State.NEW, 1400, 2.6, 8, 1000));
		computers.add(new Computer(counter.incrementAndGet(),"HP", "Pavilion", State.NEW, 900, 2.4, 8, 1000));
		computers.add(new Computer(counter.incrementAndGet(),"Dell", "E6510", State.NEW, 500, 2.3, 8, 500));
		return computers;
	}

	private static List<Phone> populateDummyPhones(){
		List<Phone> phones = new ArrayList<Phone>();
		
		phones.add(new Phone(counter.incrementAndGet(),"Apple", "IPhone6s", State.NEW, 800, 5.2, false,false,15));
		phones.add(new Phone(counter.incrementAndGet(),"Huwawi", "Nexus6P", State.NEW, 700, 5.7, false,false,13));
		phones.add(new Phone(counter.incrementAndGet(),"Samsung", "Note5", State.NEW, 600, 5.7, false,false,14));
		phones.add(new Phone(counter.incrementAndGet(),"HTC", "M9", State.NEW, 580, 5.5, true,true,16));
		phones.add(new Phone(counter.incrementAndGet(),"LG", "G4", State.NEW, 550, 5.7, true,true,15));
		return phones;
	}


	private static List<Printer> populateDummyPrinters(){
		List<Printer> printers = new ArrayList<Printer>();
		
		printers.add(new Printer(counter.incrementAndGet(),"HP", "OfficeJet 7500A", State.NEW, 200, "A4", "InkJet",15));
		printers.add(new Printer(counter.incrementAndGet(),"Brother", "J6520dw", State.NEW, 180, "A4", "InkJet",12));
		printers.add(new Printer(counter.incrementAndGet(),"EPSON", "XP-820", State.NEW, 210, "A4", "InkJet",14));
		return printers;
	}

}
