package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_technology")
public class Technology extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

	@Column(name = "screen") // 3
	private String screen;

	@Column(name = "operator_system") // 3
	private String operatorSystem;

	@Column(name = "ram") // 3
	private String ram;

	@Column(name = "pin") // 3
	private String pin;

	@Column(name = "design") // 3
	private String design;

	@Column(name = "size_weight") // 3
	private String sizeWeight;

	@Column(name = "material") // 3
	private String material;

	@Column(name = "release_time") // 3
	private String releaseTime;

//	----------------	PHONE	--------------------

	@Column(name = "front_camera")
	private String frontCamera;

	@Column(name = "behind_camera")
	private String behindCamera;

	@Column(name = "chip")
	private String chip;

	@Column(name = "internal_memory")
	private String internalMemory;

	@Column(name = "sim")
	private String sim;

//	----------------	LAPTOP	--------------------

	@Column(name = "cpu")
	private String cpu;

	@Column(name = "hard_ware")
	private String hardWare;

	@Column(name = "card")
	private String card;

	@Column(name = "special")
	private String special;

	public Technology() {
		// TODO Auto-generated constructor stub
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
