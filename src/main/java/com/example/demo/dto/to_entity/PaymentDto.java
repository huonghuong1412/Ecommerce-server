package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.PaymentMethod;

public class PaymentDto extends AbstractDTO<PaymentDto> {

	private Integer type;
	private String bankName;
	private PaymentMethodDto method;
	private String datePayment;
	private String tradingCode;
	private Integer status;

	public PaymentDto() {
		// TODO Auto-generated constructor stub
	}

	public PaymentDto(Payment entity) {
		super();
		this.type = entity.getType();
		this.bankName = entity.getBankName();
		this.method = new PaymentMethodDto();
		if (method != null) {
			PaymentMethod pmethod = entity.getMethod();
			this.method = new PaymentMethodDto(pmethod);
		}
		this.datePayment = entity.getDatePayment();
		this.tradingCode = entity.getTradingCode();
		this.status = entity.getStatus();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public PaymentMethodDto getMethod() {
		return method;
	}

	public void setMethod(PaymentMethodDto method) {
		this.method = method;
	}

	public String getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}

	public String getTradingCode() {
		return tradingCode;
	}

	public void setTradingCode(String tradingCode) {
		this.tradingCode = tradingCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
