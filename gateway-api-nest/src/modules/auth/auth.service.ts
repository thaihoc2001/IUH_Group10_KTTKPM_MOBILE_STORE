import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { environment } from "src/config/environment";
import { LoginRequestModel, RefreshTokenModel, RegisterRequestModel } from "src/dto";

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

    async login(loginRequestModel: LoginRequestModel) {
        const url = `${environment.API_Service_Express}/auth/login`;
        return await this.httpService.post(url, loginRequestModel, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async register(registerRequestModel: RegisterRequestModel) {
        const url = `${environment.API_Service_Express}/auth/register`;
        return await this.httpService.post(url, registerRequestModel, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).toPromise();
    }

    async refreshToken(refreshTokenModel: RefreshTokenModel) {
        const url =  `${environment.API_Service_Express}/auth/refresh-token`;
        return await this.httpService.post(url, refreshTokenModel).toPromise();
    }
}