package com.synerzip.billfold.receiver.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.synerzip.billfold.receiver.entity.Transaction;
import com.synerzip.billfold.user.entity.UserProfile;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	public Transaction findByPayerProfile(UserProfile payerProfile);
	
	public Transaction findByReceiverProfile(UserProfile receiverProfile);

	 @Query("FROM Transaction t WHERE t.payerProfile.id=:userId and t.status=:status")
	 public Transaction findOpenTransactionForPayer(@Param("userId")Long userId,@Param("status")String status);
	 
	 
}
