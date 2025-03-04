package com.ShoppyCart.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.Vendor;

@Repository
public class VendorDao {

		Session session;
		
		public VendorDao(SessionFactory sessionFactory) {
			this.session=sessionFactory.openSession();
		}
	
	public Vendor saveVendor(Vendor vendor) {
		Transaction tnx= session.beginTransaction();
		session.persist(vendor);
		tnx.commit();
		return vendor;
	}

	public List<Vendor> getAllVendors() {
		String query="select * from vendors";
		List<Vendor> list= new ArrayList<>();
		
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Vendor v= new Vendor(); 
					v.setVender_id(result.getString(""));
					v.setEmail(result.getString(""));
					v.setFullname(result.getString(""));
					v.setPincode( result.getInt(""));
					v.setMobile(result.getLong(""));
					v.setPassword("******");
					list.add(v);
					v=null;
			}
		});
		return list;
	}

	public Vendor getVendorById(String vendorId) {
		String query="select * from vendor where venderId=?";
		Vendor vendor= new Vendor(); 
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, vendorId);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				vendor.setVender_id(result.getString("venderId"));
				vendor.setEmail(result.getString("email"));
				vendor.setFullname(result.getString("fullname"));
				vendor.setPincode( result.getInt("pincode"));
				vendor.setMobile(result.getLong("mobile"));
				vendor.setPassword(result.getString("password"));
			}
		});
		return vendor;
	}

	public Vendor getVendorByEmail(String email) {
		String query="select * from vendor where email=?";
		Vendor vendor= new Vendor(); 
		session.doWork(connection->{
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				vendor.setVender_id(result.getString("venderId"));
				vendor.setEmail(result.getString("email"));
				vendor.setFullname(result.getString("fullname"));
				vendor.setPincode( result.getInt("pincode"));
				vendor.setMobile(result.getLong("mobile"));
				vendor.setPassword(result.getString("password"));
			}
		});
		return vendor;
	}

	public Vendor saveAndUpdateVendor(Vendor vendor) {
		Transaction tnx= session.beginTransaction();
		session.merge(vendor);
		tnx.commit();
		return vendor;
	}
	
}
