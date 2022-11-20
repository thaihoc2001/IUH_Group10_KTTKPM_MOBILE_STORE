import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { environment } from "src/config/environment";
import { createOrderRequestModel, OrderByUserFilters, OrderFilters } from "../../dto";

@Injectable()
export class OrderService {
    constructor(private readonly httpService: HttpService) {}

    async getOrders(apiKey: string, filters: OrderFilters) {
        const url = `${environment.API_Service_Spring_Boot}/orders`;
        return await this.httpService.get(url, {
            headers: {
                'Content-Type': 'application/json',
                'X-API-Key': apiKey,
            },
            params: filters
        }).toPromise();
    }

    async getOrdersByUser(apiKey: string, filters: OrderByUserFilters) {
        const url = `${environment.API_Service_Spring_Boot}/orders/user/me`;
        return await this.httpService.get(url, {
            headers: {
                'Content-Type': 'application/json',
                'X-API-Key': apiKey,
            },
            params: filters
        }).toPromise();
    }

    async getOrderById(apiKey: string, id: string) {
        const url = `${environment.API_Service_Spring_Boot}/orders/${id}`;
        return await this.httpService.get(url, {
            headers: {
                'Content-Type': 'application/json',
                'X-API-Key': apiKey,
            }
        }).toPromise();
    }

    async createOrder(apiKey: string, body: createOrderRequestModel) {
        const url = `${environment.API_Service_Spring_Boot}/orders`;
        return await this.httpService.post(url, body, {
            headers: {
                'Content-Type': 'application/json',
                'X-API-Key': apiKey,
            }
        }).toPromise();
    }
}