import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { environment } from "src/config/environment";

@Injectable()
export class AuthService {
    constructor(private readonly httpService: HttpService) {}

    async authentication(token: string) {
        const url = `${environment.API_Service_Express}/auth/authentication`;
        return await this.httpService.post(url, null, {
            headers: {
                Authorization: `Bearer ${token}`,
            }
        }).toPromise();
    }
}