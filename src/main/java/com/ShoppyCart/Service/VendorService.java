package com.ShoppyCart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Repository.VendorDao;
import com.ShoppyCart.commonUse.Common;
import com.ShoppyCart.entity.Vendor;

@Service
public class VendorService {
@Autowired
	VendorDao vendorDao;
private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
	
@CachePut(value = "Vendor", key = "#vendor.venderId")
	public Vendor saveVendor(Vendor vendor) {
		 vendor.setVender_id(Common.genrateVendorId());
		 vendor.setPassword(encoder.encode(vendor.getPassword()));
		return vendorDao.saveVendor(vendor);
	}

	@Cacheable
	public List<Vendor> getAllVendors() {
		return vendorDao.getAllVendors();
	}

	@Cacheable(value = "Vendor", key = "#vendorId")
	public Vendor getVendorById(String vendorId) {
		return vendorDao.getVendorById(vendorId);
	}

	
	@Cacheable(value = "Vendor", key = "#email")
	public Vendor getVendorByEmail(String email) {
		return vendorDao.getVendorByEmail(email);
	}
	
	
	
	@CachePut(value = "Vendor", key = "#vendors.venderId")
	public Vendor updateVendor(Vendor vendors) {
		Vendor vendor1 = getVendorById(vendors.getVenderId());
				vendors.setPassword(vendor1.getPassword());
				vendors.setEmail(vendor1.getEmail());
		return vendorDao.saveVendor(vendors);
	}
	
	
	@CachePut(value = "Vendor", key = "#vendor.venderId")
	public Vendor saveAndUpdateVendor(Vendor vendor) {
		return vendorDao.saveAndUpdateVendor(vendor);
	}
	
	
	
}
