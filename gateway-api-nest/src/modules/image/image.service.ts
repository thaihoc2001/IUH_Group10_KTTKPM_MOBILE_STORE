import { HttpService } from "@nestjs/axios";
import { Injectable } from "@nestjs/common";
import * as FormData from 'form-data'
import { map, Observable } from "rxjs";
import { environment } from "src/config/environment";

@Injectable()
export class ImageService {
  constructor(private readonly httpService: HttpService) {}

  async createImage(apiKey: string,file: Express.Multer.File, productId: string, type: string) {
    let body = new FormData();
    body.append('file', file.buffer, file.originalname);

    const url = `${environment.API_Service_Spring_Boot}/files`;
        return await this.httpService.post(url, body, {
            headers: {
                'X-API-Key': apiKey,
                'Content-Type': 'multipart/form-data'
            },
            params: {
                productId,
                type
            }
        }).toPromise();
  }
}