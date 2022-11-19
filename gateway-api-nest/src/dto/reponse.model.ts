import { ApiProperty } from "@nestjs/swagger";

export class ErrorResponse {
  @ApiProperty()
  code: number;
  @ApiProperty()
  message: string;
  @ApiProperty()
  errors: [Error]
}

export class Error {
@ApiProperty()
reason: string;
@ApiProperty()
message: string
}

export class ResponseModel<T> {
    @ApiProperty()
    id: string;
    @ApiProperty()
    data: T;
    @ApiProperty()
    error: ErrorResponse
}

