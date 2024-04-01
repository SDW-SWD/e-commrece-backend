package edu.icet.clothify.repository;

import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.util.enums.OrderStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Billing Information Repository Testing")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BillingInfoRepositoryTest {
    @Autowired
    BillingInfoRepository billingInfoRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrdersRepository ordersRepository;


    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class SaveStock {

        @Test
        @Order(1)
        @DisplayName("Save BillingInfo Repository")
        public void BillingInfoRepository_SaveBillingInfo_ReturnStockObject() {
            //Given
            Customer customer= Customer.builder().id(null).build();
            Customer save = customerRepository.save(customer);

            Orders orders= Orders.builder().id(null).status(OrderStatus.valueOf("DELIVERED")).Tot(200.00).build();
            Orders save1 = ordersRepository.save(orders);
            //When
            BillingInfo billingInfo = BillingInfo.builder().id(null).address("Mount-Lavinia").phone("0777007987").customer(Customer.builder().id(save.getId()).build()).orders(Orders.builder().status(save1.getStatus()).id(save1.getId()).build()).build();
            BillingInfo saved = billingInfoRepository.save(billingInfo);
            //Then
            Assertions.assertEquals(billingInfo.getId(), saved.getId());
            Assertions.assertEquals(billingInfo.getCustomer().getId(),saved.getCustomer().getId());
            Assertions.assertEquals(billingInfo.getCustomer().getId(),saved.getOrders().getId());


        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class UpdateStock{

        @Test
        @Order(1)
        @DisplayName("Update BillingInfo Repository")
        public void BillingInfoRepository_UpdateStock_ReturnStockObject(){
            //Given
            BillingInfo billingInfo = BillingInfo.builder().id(null).address("Mount-Lavinia").phone("0777007987").customer(null).orders(null).build();


            //When
            BillingInfo saved = billingInfoRepository.save(billingInfo);
            saved.setAddress("Gall-Face");
            saved.setPhone("0772357299");
            //Then
            Assertions.assertEquals(saved.getAddress(),"Gall-Face");
            Assertions.assertEquals(saved.getPhone(),"0772357299");
        }
    }


}
