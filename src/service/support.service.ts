import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import {SupportDTO} from 'src/dto/supportdto';
import {UserDTO} from "../dto/userdto";

@Injectable({
  providedIn: 'root'
})
export class SupportService extends AbstractService<SupportDTO> {

  constructor(http: HttpClient) {
    super(http);
      this.microservicetype = "supports";
      this.name= "gentest";
      this.port= "8080";
   }
    auth() {
        const user = JSON.parse(localStorage.getItem('currentUser')) as UserDTO;
        if (user) {
            return 'Bearer ' + user.authorities;
        } else {
            return '';
        }
    }
}
