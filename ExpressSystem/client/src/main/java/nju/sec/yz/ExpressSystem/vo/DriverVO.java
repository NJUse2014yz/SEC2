package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.Sex;

public class DriverVO {
	//司机编号、姓名、出生日期、身份证号、手机 、性别、行驶证期限
	
		//司机编号
		private String id;
		
		//
		private String name;
		
		//出生日期(日期格式规则：8位数字，如20151007)
		private String birthDate;
		
		//身份证号(18位)
		private String personID;
		
		//手机
		private String phoneNumber;
		
		//性别(下拉框选择)
		private Sex sex;

		//行驶证期限(日期)
		private String licenseDeadLine;

		public DriverVO(String id, String name, String birthDate, String personID, String phoneNumber, Sex sex,
				String licenseDeadLine) {
			super();
			this.id = id;
			this.name = name;
			this.birthDate = birthDate;
			this.personID = personID;
			this.phoneNumber = phoneNumber;
			this.sex = sex;
			this.licenseDeadLine = licenseDeadLine;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}

		public String getPersonID() {
			return personID;
		}

		public void setPersonID(String personID) {
			this.personID = personID;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Sex getSex() {
			return sex;
		}

		public void setSex(Sex sex) {
			this.sex = sex;
		}

		public String getLicenseDeadLine() {
			return licenseDeadLine;
		}

		public void setLicenseDeadLine(String licenseDeadLine) {
			this.licenseDeadLine = licenseDeadLine;
		}
		
}
