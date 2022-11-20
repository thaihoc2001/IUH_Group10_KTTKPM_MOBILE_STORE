import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { map, Observable } from "rxjs";
import { environment } from "src/config/environment";
import { BrandFilters, CreateBrandRequestModel } from "src/dto/brand.model";

@Injectable()
export class BrandService {
  constructor(private readonly httpService: HttpService) {}

  async getAll(filters: BrandFilters) {
    const url = `${environment.API_Service_Spring_Boot}/brands`;
    return await this.httpService.get(url, {
        headers: {
            'Content-Type': 'application/json'
        },
        params: filters
    }).toPromise();
  }

  async createBrand(apiKey: string,brand: CreateBrandRequestModel) {
    const url = `${environment.API_Service_Spring_Boot}/brands`;
    return await this.httpService.post(url, brand, {
        headers: {
            'X-API-Key': apiKey,
            'Content-Type': 'application/json'
        }
    }).toPromise();
  }
}