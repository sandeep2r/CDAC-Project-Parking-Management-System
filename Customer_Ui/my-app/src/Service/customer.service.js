
import axios from "axios";

const BASE_API_URL = "http://localhost:8080/customer";

class CustomerService {
    saveCustomer(customer) {
        return axios.post(BASE_API_URL + "/add", customer);
    }

    getCustomerById(id) {
        return axios.get(BASE_API_URL + "/" + id);

    }

    login(authRequest) {
        return axios.post(BASE_API_URL + "/login", authRequest);
    }

    // getAllCustomer(){
    //     return axios.get(BASE_API_URL+"/list");
    // }

    // getEmployeeById(id){
    //     return axios.get(BASE_API_URL+"/"+id);
    // }

    // deleteEmployee(id){
    //     return axios.get(BASE_API_URL+"/delete/"+id);
    // }

    // updateEmployee(id,employee){
    //     return axios.post(BASE_API_URL+"/update/"+id,employee);
    //     }

}

export default new CustomerService();