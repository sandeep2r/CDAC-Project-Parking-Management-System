package com.parking.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PAYMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment extends BaseEntity{

    @NotNull(message = "Payment type cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = true, unique = true)
    private Integer transactionId;

    //@NotBlank(message = "Total amount cannot be blank")
    // Why NOTBlank does not work here, it gives error in post --> add payment
    @NotNull(message = "Total amount cannot be null")
    @Positive(message = "Total amount must be greater than 0")
    @Column(nullable = false)
    private Integer totalAmount;

    //@NotBlank(message = "Payment status cannot be blank")
    @NotNull(message = "Payment status cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

	public Payment(@NotNull(message = "Payment type cannot be null") PaymentType paymentType, Integer transactionId,
			@NotNull(message = "Total amount cannot be null") @Positive(message = "Total amount must be greater than 0") Integer totalAmount,
			@NotNull(message = "Payment status cannot be null") PaymentStatus paymentStatus) {
		super();
		this.paymentType = paymentType;
		this.transactionId = transactionId;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Payment() {
		super();
	}

   
}