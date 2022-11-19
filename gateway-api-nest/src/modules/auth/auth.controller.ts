import { Controller } from "@nestjs/common";
import { ApiBearerAuth, ApiTags } from "@nestjs/swagger";
import { AuthService } from "./auth.service";


@ApiBearerAuth()
@ApiTags('api/auth')
@Controller('api/auth')
export class AuthController {
    constructor(private authService: AuthService) {}
}