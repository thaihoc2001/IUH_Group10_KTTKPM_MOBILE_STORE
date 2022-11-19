import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { ProductFilters } from "src/dto";
import { environment } from "../../config/environment";

@Injectable()
export class ProductService {
    constructor(private readonly httpService: HttpService) {}

    async createProduct(apiKey: string,product: any) {
        const url = `${environment.API_Service_Spring_Boot}/products`;
        return await this.httpService.post(url, product, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async getAllProduct(filters: ProductFilters) {
        const url = `${environment.API_Service_Spring_Boot}/products`;
        return await this.httpService.get(url, {
            headers: {
                'Content-Type': 'application/json'
            },
            params: filters
        }).toPromise();
    }

    async updateProduct(apiKey: string,product: any) {
        const url = `${environment.API_Service_Spring_Boot}/products`;
        return await this.httpService.put(url, product, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async getByProductId(productId: string) {
        const url = `${environment.API_Service_Spring_Boot}/products/${productId}`;
        return await this.httpService.get(url).toPromise();
    }
}