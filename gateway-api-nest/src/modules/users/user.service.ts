import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import { map, Observable } from "rxjs";
import { environment } from "src/config/environment";

@Injectable()
export class UserService {
  constructor(private readonly httpService: HttpService) {}

  async findUserByUserId() {
    const url = `${environment.API_Service_Express}/users/9d1f71b4-dbf7-4c67-8c88-9a6878b8f7c4`
    return await this.httpService.get(url).toPromise();
  }
}