package com.intelliatech.LibraryManagement.service.helper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
 
	 private static final Integer EXPIRE_MINS = 3;

	 private LoadingCache<String, Integer> otpCache;

	 public OtpService(){
	 super();
	 otpCache = CacheBuilder.newBuilder().
	     expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
	      public Integer load(String key) {
	             return 0;
	       }
	   });
	 }
	 
	 public int generateOTP(String key){
	Random random = new Random();
	int otp = 100000 + random.nextInt(900000);
	System.out.println(key);
	System.out.println(otp);
	otpCache.put(key, otp);
	
	return otp;
	 }

	 public int getOtp(String key){ 
	try{
		
	 return otpCache.get(key); 
	}catch (Exception e){
	 return 0; 
	}
	 }
	 
	 public void clearOTP(String key){ 
		 
	System.out.println("clear otp of "+key);
	  otpCache.invalidate(key);
	  }
	 
}