import { Injectable } from '@angular/core';
import {AbstractService} from './abstractservice';
import {ProgettiDTO} from 'src/dto/progettidto';
import {UserDTO} from 'src/dto/userdto';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProgettiService extends AbstractService<ProgettiDTO> {

  constructor(http: HttpClient) {

    super(http);
    this.microservicetype = "progettis";
    this.name= "gentest";
    this.port= "8080";
  }
  // getAllById(userDTO: UserDTO): Observable<ProgettiDTO[]>{
  //   return this.http.post<ProgettiDTO[]>('http://localhost:8080/'+this.type+'/getallbyuser',userDTO) ;
  // }
  auth() {
    const user = JSON.parse(localStorage.getItem('currentUser')) as UserDTO;
    if (user) {
      return 'Bearer ' + user.authorities;
    } else {
      return '';
    }
  }
}
