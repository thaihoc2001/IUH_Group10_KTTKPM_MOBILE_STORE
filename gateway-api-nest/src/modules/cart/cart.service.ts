import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { environment } from "src/config/environment";
import { CartDetailAddRequestModel, CartDetailRemoveRequestModel } from "../../dto";

@Injectable()
export class CartService {

    constructor(private readonly httpService: HttpService) {}

    async getCartByUser(apiKey: string) {
        const url = `${environment.API_Service_Spring_Boot}/carts`;
        return await this.httpService.get(url, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async createCart(apiKey: string) {
        const url = `${environment.API_Service_Spring_Boot}/carts`;
        return await this.httpService.post(url,null, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async addToCart(apiKey: string, requestModel: CartDetailAddRequestModel) {
        const url = `${environment.API_Service_Spring_Boot}/carts/cart-detail/add`;
        return await this.httpService.post(url, requestModel, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async removeToCart(apiKey: string, requestModel: CartDetailRemoveRequestModel) {
        const url = `${environment.API_Service_Spring_Boot}/carts/cart-detail/remove`;
        return await this.httpService.post(url, requestModel, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }
}