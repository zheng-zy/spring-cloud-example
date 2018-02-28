package com.example;

import com.google.common.base.Preconditions;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/2/28.
 */
public class TestAll {

    @Test
    public void test() {
        Integer uid = 1;
        Address address = new Address();
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Preconditions.checkNotNull(uid);
        Set<ConstraintViolation<Address>> sets = validator.validateProperty(address, "province");
        for (ConstraintViolation<Address> set : sets) {
            System.out.println("set = " + set.getMessage());
        }
    }

    class Address {
        private Integer id;
        @NotNull
        private String province;//省
        @NotNull
        private String city;//市
        private String county;//区
        private Boolean isDefault;//是否是默认地址

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public Boolean getDefault() {
            return isDefault;
        }

        public void setDefault(Boolean aDefault) {
            isDefault = aDefault;
        }
    }

}
