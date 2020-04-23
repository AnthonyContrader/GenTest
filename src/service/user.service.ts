import { Injectable } from '@angular/core';
import { UserDTO } from '../dto/userdto';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from '../dto/logindto';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicetype = "api";
    this.name= "users";
    this.port = "8080"
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
    return this.http.get('http://localhost:8080/api/users/' + username,{
      headers: {
        Authorization: this.auth()
      }
    });
  }

  login(loginDTO: LoginDTO): Observable<UserDTO> {
    return this.http.post<any>('http://localhost:8080/' + this.microservicetype + '/authenticate', loginDTO)
  }

  getAll() {
    return this.http.get<UserDTO[]>("http://localhost:8080/api/users", {
      headers: {
        Authorization: this.auth()
      }
    });
  }
}
