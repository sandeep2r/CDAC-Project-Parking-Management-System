import axios from "axios";

const BASE_API_URL="http://localhost:7070/vehicle";


class VehicleService{

    saveVehicle(vehicle){
        console.log("cust"+vehicle.customer);
        console.log("model"+vehicle.modelName);
        console.log("number"+vehicle.vehicleNumber);
        console.log("vehtype",vehicle.vehicleType);

        return axios.post(BASE_API_URL+"/add",vehicle);
        
        
    }
    
    getVehicleById(id){
        console.log(id);
        return axios.get(BASE_API_URL+"/Dto/"+id);

    }

    deleteVehicle(vehicleNumber){
        return axios.delete(BASE_API_URL+"/"+vehicleNumber);
    }

    getAllVehicle(id){
        return axios.get(BASE_API_URL+"/customer/Dto/"+id);
    }

}

export default new VehicleService();