import axios from "axios";

const BASE_API_URL="http://localhost:7070/bookings";

class BookingService{
    saveBooking(book){
        console.log("num",book.vehicleNumber);
        console.log("in",book.checkInTime);
        console.log("out",book.checkOutTime);
        return axios.post(BASE_API_URL+"/add",book);
    }
}

export default new BookingService();