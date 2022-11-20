import { ApiProperty } from "@nestjs/swagger";

export class BrandFilters {
    @ApiProperty({
        required: false
    })
    searchText: string;
    @ApiProperty({
        default: 0,
    })
    page: number;
    @ApiProperty({
        default: 20
    })
    size: number;
    @ApiProperty({
        description: "fieldname,asc|desc",
        required: false,
        isArray: true
    })
    sort: [string];
}

export class CreateBrandRequestModel {
    @ApiProperty()
    name: string;
}