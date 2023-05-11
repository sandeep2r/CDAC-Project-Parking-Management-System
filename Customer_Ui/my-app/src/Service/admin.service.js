import axios from "axios";

const BASE_API_URL="http://localhost:7070/admin";


class AdminService{
    login(authRequest){
        return axios.post(BASE_API_URL+"/login",authRequest);
    }
}
export default new AdminService();