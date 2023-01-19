package kurswork.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import kurswork.order.controller.AuthController;
import kurswork.order.controller.DishController;
import kurswork.order.dto.DishDto;
import kurswork.order.dto.UserDto;
import kurswork.order.repository.DishRepository;
import kurswork.order.repository.OrderRepository;
import kurswork.order.repository.RoleRepository;
import kurswork.order.repository.UserRepository;
import kurswork.order.service.DishService;
import kurswork.order.service.OrderService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DishController.class)
public class DishTestController {
    @MockBean
    private DishService dishService;
    @MockBean
    private DishRepository dishRepository;
    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy filterChainProxy;
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    @WithAnonymousUser
    public void whenAnonymousAccessWaiter_thenIsUnauthorized() throws Exception {
        mvc.perform(get("/order"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    @WithAnonymousUser
    public void whenAnonymousAccessCook_thenIsUnauthorized() throws Exception {
        mvc.perform(get("/orders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void whenUserAccessOrder_thenViewMenu() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/order");
        ResultActions result = mvc.perform(request);
        result.andExpect(MockMvcResultMatchers.view().name("order"));
    }

    @Test
    public void testNew(){
        DishController dishC=new DishController(dishService,dishRepository,orderRepository,orderService);
        String result=dishC.newDishSave(new DishDto(16L, "Капуста", "100", "Капуста"),null,null);
        assertEquals(result,"redirect:/admin/menu");
    }
}
