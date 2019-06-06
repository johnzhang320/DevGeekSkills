package com.locale.message.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressUtils {
         private static final String IP_ADDRESS = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
         private static final String SLASH_FORMAT = IP_ADDRESS + "/(\\d{1,3})";
         private static final Pattern addressPattern = Pattern.compile(IP_ADDRESS);
         private static final Pattern cidrPattern = Pattern.compile(SLASH_FORMAT);
         private static final int NBITS = 32;
         
         private int netmask = 0;
         private int address = 0;
         private int network = 0;
         private int broadcast = 0;
         private String ipAddress=null; 
         
         /** Whether the broadcast/network address are included in host count */
         private boolean inclusiveHostCount = false;
         public IPAddressUtils() {}
         
         /**
         * Constructor that takes a CIDR-notation string, e.g. "192.168.0.1/16"
         * @param cidrNotation A CIDR-notation string, e.g. "192.168.0.1/16"
         * @throws IllegalArgumentException if the parameter is invalid,
         * i.e. does not match n.n.n.n/m where n=1-3 decimal digits, m = 1-3 decimal digits in range 1-32
         */
         public IPAddressUtils(String cidrNotation) {
        	 ipAddress =cidrNotation; 
        	 calculate(cidrNotation);
         }
         
         /**
         * Constructor that takes a dotted decimal address and a dotted decimal mask.
         * @param address An IP address, e.g. "192.168.0.1"
         * @param mask A dotted decimal netmask e.g. "255.255.0.0"
         * @throws IllegalArgumentException if the address or mask is invalid,
         * i.e. does not match n.n.n.n where n=1-3 decimal digits and the mask is not all zeros
         */
         public IPAddressUtils(String address, String mask) {
        	 calculate(toCidrNotation(address, mask));
         }


         /**
         * Returns <code>true</code> if the return value of {@link SubnetInfo#getAddressCount()}
         * includes the network address and broadcast addresses.
         * @since 2.2
         */
         public boolean isInclusiveHostCount() {
         return inclusiveHostCount;
         }
         
         /**
         * Set to <code>true</code> if you want the return value of {@link SubnetInfo#getAddressCount()}
         * to include the network and broadcast addresses.
         * @param inclusiveHostCount
         * @since 2.2
         */
         public void setInclusiveHostCount(boolean inclusiveHostCount) {
         this.inclusiveHostCount = inclusiveHostCount;
         }
           
         /**
         * Convenience container for subnet summary information.
         *
         */
           public final class SubnetInfo {
                 private SubnetInfo() {}
                 
                 private int netmask() { return netmask; }
                 private int network() { return network; }
                 private int address() { return address; }
                 private int broadcast() { return broadcast; }
                 
                 private int low() {
                 return (isInclusiveHostCount() ? network() :
                 broadcast() - network() > 1 ? network() + 1 : 0);
                 }
                 
                 private int high() {
                 return (isInclusiveHostCount() ? broadcast() :
                 broadcast() - network() > 1 ? broadcast() -1 : 0);
                 }
                 
                 /**
                 * Returns true if the parameter <code>address</code> is in the
                 * range of usable endpoint addresses for this subnet. This excludes the
                 * network and broadcast adresses.
                 * @param address A dot-delimited IPv4 address, e.g. "192.168.0.1"
                 * @return True if in range, false otherwise
                 */
                 public boolean isInRange(String address) {
                 return isInRange(toInteger(address));
                 }
                 
                 private boolean isInRange(int address) {
                 int diff = address - low();
                 return (diff >= 0 && (diff <= (high() - low())));
                 }
                 
                 public String getBroadcastAddress() {
                 return format(toArray(broadcast()));
                 }
                 
                 public String getNetworkAddress() {
                 return format(toArray(network()));
                 }
                 
                 public String getNetmask() {
                 return format(toArray(netmask()));
                 }
                 
                 public String getAddress() {
                 return format(toArray(address()));
                 }
                 
                 /**
                 * Return the low address as a dotted IP address.
                 * Will be zero for CIDR/31 and CIDR/32 if the inclusive flag is false.
                 *
                 * @return the IP address in dotted format, may be "0.0.0.0" if there is no valid address
                 */
                 public String getLowAddress() {
                 return format(toArray(low()));
                 }
                 
                 /**
                 * Return the high address as a dotted IP address.
                 * Will be zero for CIDR/31 and CIDR/32 if the inclusive flag is false.
                 *
                 * @return the IP address in dotted format, may be "0.0.0.0" if there is no valid address
                 */
                 public String getHighAddress() {
                 return format(toArray(high()));
                 }
                 
                 /**
                 * Get the count of available addresses.
                 * Will be zero for CIDR/31 and CIDR/32 if the inclusive flag is false.
                 * @return the count of addresses, may be zero.
                 */
                 public int getAddressCount() {
                 int count = broadcast() - network() + (isInclusiveHostCount() ? 1 : -1);
                 return count < 0 ? 0 : count;
                 }
                 
                 public int asInteger(String address) {
                 return toInteger(address);
                 }
                 
                 public String getCidrSignature() {
                 return toCidrNotation(
                		 format(toArray(address())),
                		 format(toArray(netmask())));
                 }
                 
                 public String[] getAllAddresses() {
                	 int ct = getAddressCount();
                	 String[] addresses = new String[ct];
                	 if (ct == 0) {
                		 return addresses;
                	 }
                	 for (int add = low(), j=0; add <= high(); ++add, ++j) {
                		 addresses[j] = format(toArray(add));
                	 }
                	 return addresses;
                 }
                 
              
                 
                 /**
                 * {@inheritDoc}
                  * @since 2.2
                  */
                  @Override
                  public String toString() {
                  final StringBuilder buf = new StringBuilder();
                  	buf.append("CIDR Signature:\t[").append(getCidrSignature()).append("]")
                  	.append(" Netmask: [").append(getNetmask()).append("]\n")
                  	.append("Network:\t[").append(getNetworkAddress()).append("]\n")
                  	.append("Broadcast:\t[").append(getBroadcastAddress()).append("]\n")
                  	.append("First Address:\t[").append(getLowAddress()).append("]\n")
                  	.append("Last Address:\t[").append(getHighAddress()).append("]\n")
                  	.append("# Addresses:\t[").append(getAddressCount()).append("]\n");
                  	return buf.toString();
                  }
               
           
            }   // internal class
                  
                  /**
                  * Return a {@link SubnetInfo} instance that contains subnet-specific statistics
                  * @return new instance
                  */
                  public final SubnetInfo getInfo() { return new SubnetInfo(); }
                  /**
                   *  validation pure IP address without CIDR
                   */
                  private Pattern pattern;
                  private Matcher matcher;
               
                  private static final String IPADDRESS_PATTERN = 
              		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
              		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
              		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
              		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
               
                 //    .matches("\A(25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3}\z")) {
               
                  public boolean isValidDomainName(String currentDomain) {
                 	 String[] words = currentDomain.split("\\.");
                  	 if (words.length>1) {
                 		int len = words.length-1;
                 		if (words[len].length()>3 || words[len].length()==1) {
                 			return false;
                 		}
                 		 
                 	 } else {
                 		 return false;
                 	 }
                 	  
                 	 
                 	 return true;
                  }
                 
                  public boolean isValidIPAddress(final String ip){	
                	  if (null==ip) {
                		  return false;
                	  }
                	  String ipv4[] = ip.split("/");
                
                	  if (ipv4.length==2) {
                		  return isValidCIDR(ip);
                	  } else if (isValidSimpleIP(ip)) {
                		  return true;
                	  } 
                	  return false;
                  }
                  public boolean isValidSimpleIPv6(final String ip) {
                	  if (null==ip) {
                		  return false;
                	  }
                	  String ipv6[] = ip.split("\\:");
                	  if (null!=ipv6 || ipv6.length>1) {
                		  return true;
                	  }
                	  return false;
                  }
                  
                  public boolean isValidSimpleIP(final String ip){	
                	  if (null==ip) {
                		  return false;
                	  }
                	  
                	  pattern = Pattern.compile(IPADDRESS_PATTERN);
              	  	  matcher = pattern.matcher(ip);
              	  	return matcher.matches();	    	    
                  }
                  
                  public boolean isValidCIDR(final String cidrIP) {
                	  
                	  if (null==cidrIP) {
                		  return false;
                	  }
                	  String cidr[] = cidrIP.split("/");
                   	  if (cidr.length!=2) {
                		  return false;
                	  }
                	  //Check first part
                	  if (!isValidSimpleIP(cidr[0])) {
                		  return false;
                	  }
                	
                	  try {
                		  Integer dd=Integer.parseInt(cidr[1]);
                	  } catch (Exception e) {
                		  return false;
                	  }
                	  return true;
                  }
                  
                  /**
                  * Initialize the internal fields from the supplied CIDR mask
                  */
                  public boolean calculate(String mask) {                	  
                	  
                	  
                	  Matcher matcher = cidrPattern.matcher(mask);
                  
                	  if (matcher.matches()) {
                		  address = matchAddress(matcher);
                  
                		  /* Create a binary netmask from the number of bits specification /x */
                		  int cidrPart = rangeCheck(Integer.parseInt(matcher.group(5)), 1, NBITS);
                		  for (int j = 0; j < cidrPart; ++j) {
                			  netmask |= (1 << 31-j);
                		  }
                  
                		  /* Calculate base network address */
                		  network = (address & netmask);
                  
                		  /* Calculate broadcast address */
                		  broadcast = network | ~(netmask);
                	  } else {
                		  //throw new IllegalArgumentException("Could not parse [" + mask + "]");
                		  return false;
                	  }
                	  return true;
                 }
                  
                  /*
                  * Convert a dotted decimal format address to a packed integer format
                  */
                  public int toInteger(String address) {
                	  Matcher matcher = addressPattern.matcher(address);
                	  if (matcher.matches()) {
                		  return matchAddress(matcher);
                	  } else {
                		  throw new IllegalArgumentException("Could not parse [" + address + "]");
                	  }
                  }
                  
                  /*
                  * Convenience method to extract the components of a dotted decimal address and
                  * pack into an integer using a regex match
                 */
                 private int matchAddress(Matcher matcher) {
                 int addr = 0;
                 for (int i = 1; i <= 4; ++i) {
                 int n = (rangeCheck(Integer.parseInt(matcher.group(i)), 0, 255));
                 addr |= ((n & 0xff) << 8*(4-i));
                 }
                 return addr;
                 }
                 
                 /*
                 * Convert a packed integer address into a 4-element array
                 */
                 private int[] toArray(int val) {
                 int ret[] = new int[4];
                 for (int j = 3; j >= 0; --j) {
                 ret[j] |= ((val >>> 8*(3-j)) & (0xff));
                 }
                 return ret;
                 }
                 
                 /*
                 * Convert a 4-element array into dotted decimal format
                 */
                 private String format(int[] octets) {
                 StringBuilder str = new StringBuilder();
                 for (int i =0; i < octets.length; ++i){
                 str.append(octets[i]);
                 if (i != octets.length - 1) {
                 str.append(".");
                 }
                 }
                 return str.toString();
                 }
                 
                 /*
                 * Convenience function to check integer boundaries.
                 * Checks if a value x is in the range [begin,end].
                 * Returns x if it is in range, throws an exception otherwise.
                 */
                 private int rangeCheck(int value, int begin, int end) {
                 if (value >= begin && value <= end) { // (begin,end]
                 return value;
                 }
                 
                 throw new IllegalArgumentException("Value [" + value + "] not in range ["+begin+","+end+"]");
                 }
                 
                 /*
                 * Count the number of 1-bits in a 32-bit integer using a divide-and-conquer strategy
                 * see Hacker's Delight section 5.1
                 */
                 int pop(int x) {
                 x = x - ((x >>> 1) & 0x55555555);
                 x = (x & 0x33333333) + ((x >>> 2) & 0x33333333);
                 x = (x + (x >>> 4)) & 0x0F0F0F0F;
                 x = x + (x >>> 8);
                 x = x + (x >>> 16);
                 return x & 0x0000003F;
                 }
                 
                 /* Convert two dotted decimal addresses to a single xxx.xxx.xxx.xxx/yy format
                 * by counting the 1-bit population in the mask address. (It may be better to count
                 * NBITS-#trailing zeroes for this case)
                 */
                 private String toCidrNotation(String addr, String mask) {
                 return addr + "/" + pop(toInteger(mask));
                 }
                 /**
                  * 
                  * @param IPAddress
                  * @return positive Integer
                  */
                 public int toPosInteger(String IPAddress) {
                	 int addressInt = toInteger(IPAddress);
                	 if (addressInt<0) addressInt = addressInt*(-1);
                	 return addressInt;
                 }
            public static void main(String [] args) {
            		String addr = "204.14.232.69/14"; //"2607:f8b0:400d:c01::231";//"204.14.232.69/20";
            		IPAddressUtils handler= new IPAddressUtils();
            		System.out.println("valid IP address="+handler.isValidIPAddress(addr));
            		System.out.println("valid simple IP address="+handler.isValidSimpleIP(addr));
            		System.out.println("valid simple IPv6 address="+handler.isValidSimpleIPv6(addr));
            		System.out.println("valid cidr IP address="+handler.isValidCIDR(addr));
            		handler.calculate(addr);
            		
            		System.out.print(handler.getInfo().toString());
            		String low = handler.getInfo().getLowAddress();
            		String high = handler.getInfo().getHighAddress();
            		System.out.println("First Address="+low+",Last IP = "+high);
            		String currentIP = "204.14.232.2";             		
            		System.out.println("204.14.232.2 in First Address:"+low+",Last IP: "+high+" between "+currentIP.compareTo(low)+" and "+currentIP.compareTo(high));
            		System.out.println("204.14.232.2 in First Address integer:"+handler.toInteger(low)+",Last IP Integer: "+handler.toInteger(high)+" between currentIP Integer="+handler.toInteger(currentIP));
                	
            		
            		String domain1="mail.sonicwall.com";
            		String domain2="oracle.com.t";
            		String domain3="oracle.werwerss";
            		System.out.println(domain1+" valid ? "+handler.isValidDomainName(domain1));
            		System.out.println(domain2+" valid ? "+handler.isValidDomainName(domain2));
            		System.out.println(domain3+" valid ? "+handler.isValidDomainName(domain3));
            }
                 
                 
         }
 
