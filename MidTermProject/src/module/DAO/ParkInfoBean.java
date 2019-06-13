package module.DAO;

public class ParkInfoBean {
	private String parkId;
	private int areaId;
	private String areaName;
	private String parkName;
	private int totalSpace;
	private int surplusSpace;
	private String payGuide;
	private String introduction;
	private String address;
	private double wgsX;
	private double wgsY;
	/**
	 * Constructor
	 */
	public ParkInfoBean(String parkId, int areaId, String areaName, String parkName, int totalSpace, int surplusSpace,
			String payGuide, String introduction, String address, double wgsX, double wgsY) {
		this.parkId = parkId;
		this.areaId = areaId;
		this.areaName = areaName;
		this.parkName = parkName;
		this.totalSpace = totalSpace;
		this.surplusSpace = surplusSpace;
		this.payGuide = payGuide;
		this.introduction = introduction;
		this.address = address;
		this.wgsX = wgsX;
		this.wgsY = wgsY;
	}
	public ParkInfoBean() {

	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the parkName
	 */
	public String getParkName() {
		return parkName;
	}

	/**
	 * @param parkName the parkName to set
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	/**
	 * @return the totalSpace
	 */
	public int getTotalSpace() {
		return totalSpace;
	}

	/**
	 * @param totalSpace the totalSpace to set
	 */
	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}

	/**
	 * @return the surplusSpace
	 */
	public int getSurplusSpace() {
		return surplusSpace;
	}

	/**
	 * @param surplusSpace the surplusSpace to set
	 */
	public void setSurplusSpace(int surplusSpace) {
		this.surplusSpace = surplusSpace;
	}

	/**
	 * @return the payGuide
	 */
	public String getPayGuide() {
		return payGuide;
	}

	/**
	 * @param payGuide the payGuide to set
	 */
	public void setPayGuide(String payGuide) {
		this.payGuide = payGuide;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the wgsX
	 */
	public double getWgsX() {
		return wgsX;
	}

	/**
	 * @param wgsX the wgsX to set
	 */
	public void setWgsX(double wgsX) {
		this.wgsX = wgsX;
	}

	/**
	 * @return the wgsY
	 */
	public double getWgsY() {
		return wgsY;
	}

	/**
	 * @param wgsY the wgsY to set
	 */
	public void setWgsY(double wgsY) {
		this.wgsY = wgsY;
	}

	/**
	 * @return the parkId
	 */
	public String getParkId() {
		return parkId;
	}

	/**
	 * @param parkId the parkId to set
	 */
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	/**
	 * @return the areaId
	 */
	public int getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	/*
	 * display data member
	 */
	public void showDetail() {
		System.out.println("ParkId:" + this.getParkId());
		System.out.println("AreaId:" + this.getAreaId());
		System.out.println("ParkName:" + this.getParkName());
		System.out.println("AreaName:" + this.getAreaName());
		System.out.println("TotalSpace:" + this.getTotalSpace());
		System.out.println("SurplusSpace:" + this.getSurplusSpace());
		System.out.println("PayGuide:" + this.getPayGuide());
		System.out.println("Introduction:" + this.getIntroduction());
		System.out.println("Address:" + this.getAddress());
		System.out.println("WgsX:" + this.getWgsX());
		System.out.println("WgsY:" + this.getWgsY());
	}
}
