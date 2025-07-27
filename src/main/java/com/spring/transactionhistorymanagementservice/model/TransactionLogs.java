package com.spring.transactionhistorymanagementservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction_logs", schema = "transaction_history_management_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLogs implements Serializable {
    private static final long serialVersionUID = -3563841330159316972L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotNull
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionHistory transaction_id;

    @Column(name = "log_message")
    private String logMessage;

    @Column(name = "log_level")
    private String logLevel;

    @Column(name = "loggged_at")
    private Date loggedAt;
}
