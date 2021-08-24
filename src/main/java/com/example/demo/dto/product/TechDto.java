package com.example.demo.dto.product;

import com.example.demo.entity.product.Technology;

public class TechDto {

	private String screen;
	private String operatorSystem;
	private String ram;
	private String pin;
	private String design;
	private String sizeWeight;
	private String material;
	private String releaseTime;

	// phone
	private String frontCamera;
	private String behindCamera;
	private String chip;
	private String internalMemory;
	private String sim;

	// laptop
	private String cpu;
	private String hardWare;
	private String card;
	private String special;

	public TechDto() {
		// TODO Auto-generated constructor stub
	}

	public TechDto(Technology entity) {
		// electric
		this.screen = entity.getScreen();
		this.operatorSystem = entity.getOperatorSystem();
		this.ram = entity.getRam();
		this.pin = entity.getPin();
		this.design = entity.getDesign();
		this.sizeWeight = entity.getSizeWeight();
		this.material = entity.getMaterial();
		this.releaseTime = entity.getReleaseTime();
		// phone
		this.frontCamera = entity.getFrontCamera();
		this.behindCamera = entity.getBehindCamera();
		this.chip = entity.getChip();
		this.internalMemory = entity.getInternalMemory();
		this.sim = entity.getSim();

		this.cpu = entity.getCpu();
		this.hardWare = entity.getHardWare();
		this.card = entity.getCard();
		this.special = entity.getSpecial();
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getOperatorSystem() {
		return operatorSystem;
	}

	public void setOperatorSystem(String operatorSystem) {
		this.operatorSystem = operatorSystem;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getSizeWeight() {
		return sizeWeight;
	}

	public void setSizeWeight(String sizeWeight) {
		this.sizeWeight = sizeWeight;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public String getBehindCamera() {
		return behindCamera;
	}

	public void setBehindCamera(String behindCamera) {
		this.behindCamera = behindCamera;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public String getInternalMemory() {
		return internalMemory;
	}

	public void setInternalMemory(String internalMemory) {
		this.internalMemory = internalMemory;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getHardWare() {
		return hardWare;
	}

	public void setHardWare(String hardWare) {
		this.hardWare = hardWare;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

}
