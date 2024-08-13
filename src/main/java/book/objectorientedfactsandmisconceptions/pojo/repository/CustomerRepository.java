package book.objectorientedfactsandmisconceptions.pojo.repository;

import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 손님 저장소
 */
@Getter
public class CustomerRepository {

    private static Map<String, Customer> customerMap = new HashMap<>();

    public static Map<String, Customer> getCustomerMap() {
        return customerMap;
    }

    public static void addCustomer(Customer customer) {
        customerMap.put(customer.getName(), customer);
    }

    public static Customer getCustomer(String customer) {
        Customer findCustomer = customerMap.get(customer);
        if(Objects.isNull(findCustomer)) {
            addCustomer(Customer.createCustomer(customer));
            findCustomer = customerMap.get(customer);
        }
        return findCustomer;
    }
}
