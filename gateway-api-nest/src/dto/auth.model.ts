import { ApiProperty } from "@nestjs/swagger";

export class LoginRequestModel {
    @ApiProperty()
    username: string;
    @ApiProperty()
    password: string;
}

export class RegisterRequestModel {
    @ApiProperty()
    email: string;
    @ApiProperty()
    first_name: string;
    @ApiProperty()
    last_name: string;
    @ApiProperty()
    password: string;
    @ApiProperty()
    phone: string;
    @ApiProperty()
    username: string;
}

export class RefreshTokenModel {
    @ApiProperty()
    token: string;
}