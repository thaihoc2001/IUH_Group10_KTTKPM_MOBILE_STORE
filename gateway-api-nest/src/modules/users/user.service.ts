import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { map, Observable } from "rxjs";
import { environment } from "src/config/environment";

@Injectable()
export class UserService {
  constructor(private readonly httpService: HttpService) {}

  async findUserByUserId(token: string, id: string) {
    const url = `${environment.API_Service_Express}/users/${id}`;
    return await this.httpService.get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
    }
    }).toPromise();
  }

  async getAll(token: string) {
    const url = `${environment.API_Service_Express}/users`;
    return await this.httpService.get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      }
    }).toPromise();
  }

  async getProfile(token: string) {
    const url = `${environment.API_Service_Express}/users/user/me`;
    return await this.httpService.get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      }
    }).toPromise();
  }
}