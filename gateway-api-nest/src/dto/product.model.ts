import { ApiProperty } from "@nestjs/swagger";

export class CreateProductRequestModel {
    @ApiProperty()
    name: string;
    @ApiProperty()
    type: string;
    @ApiProperty()
    price: number;
    @ApiProperty()
    status: string;
    @ApiProperty()
    description: string;
    @ApiProperty()
    screen: string;
    @ApiProperty()
    operatingSystem: string;
    @ApiProperty()
    chip: string;
    @ApiProperty()
    ram: string;
    @ApiProperty()
    internalMemory: string;
    @ApiProperty()
    rearCamera: string;
    @ApiProperty()
    frontCamera: string;
    @ApiProperty()
    brandId: string;
    @ApiProperty()
    quantity: number;
}

export class ProductFilters {

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