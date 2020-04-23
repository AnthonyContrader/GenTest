import { Injectable } from '@angular/core';
import { UserDTO } from '../dto/userdto';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from '../dto/logindto';
//import { RegisterDTO } from '../dto/registerdto';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicetype = "api";
    this.name= "users";
    
   }

   auth() {
    const user = JSON.parse(localStorage.getItem('currentUser')) as UserDTO;
    if (user) {
      return 'Bearer ' + user.authorities;
    } else {
      return '';
    }
  }

  userLogged(username: string) {
     return this.http.post('http://192.168.99.1:8080/api/users/' + username, {
       headers: {
         Authorization: this.auth()
       }
     });
   }

  login(loginDTO: LoginDTO): Observable<UserDTO> {
    return this.http.post<any>('http://192.168.99.1:8080' + this.microservicetype + '/authenticate', loginDTO)
  }

  getAll() {
    return this.http.get<UserDTO[]>("http://192.168.99.1:8080/api/users", {
      headers: {
        Authorization: this.auth()
      }
    });
  }
}
