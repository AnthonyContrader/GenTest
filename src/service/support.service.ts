import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import {SupportDTO} from 'src/dto/supportdto';

@Injectable({
  providedIn: 'root'
})
export class SupportService extends AbstractService<SupportDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type='support';
   }
}
