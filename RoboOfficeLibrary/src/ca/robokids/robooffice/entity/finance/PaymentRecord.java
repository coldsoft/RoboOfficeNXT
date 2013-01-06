
package ca.robokids.robooffice.entity.finance;

import java.sql.Date;


public class PaymentRecord {


    private int payment_record_id;
    private int payment_history_id;
    private String feeName;
    private String feeDescription;
    private float rate;
    private int multiplier;
    private float availableCredit;
    private int discountClass;
    private int discountAmount;
    private String discountReason;
    private float tax;
    private float total;
    private Date startDate;
    private Date expireDate;
 }
