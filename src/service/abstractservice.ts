import { Service } from './service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../dto/userdto';
import { JsonPipe } from '@angular/common';

export abstract class AbstractService<DTO> implements Service<DTO>{

  microservicetype: string;
  port: string;
  name: string;
  type: string;

  constructor(protected http: HttpClient) { }

  auth() {
    const user = JSON.parse(localStorage.getItem('Autoken')) as UserDTO;
    if (user) {
      console.log('Bearer ' + user.authorities);
      return 'Bearer ' + user.authorities;
    } else {
      return '';
    }
  }

  getAll(): Observable<DTO[]> {
    return this.http.get<DTO[]>('http://localhost:' + this.port + '/'+ this.name + '/' + 'api' + '/' + this.microservicetype , {
        headers: {
          Authorization : this.auth()
     }
    });
    
  }

  read(id: number): Observable<DTO> {
      return this.http.get<DTO>('http://localhost:' + this.port + '/'+ this.name + '/' + 'api' + '/' + this.microservicetype + '/' + id, {
        headers: {
          Authorization : this.auth()
    }
    });
  }

  delete(id: number): Observable<any> {
      return this.http.delete('http://localhost:' + this.port + '/' + this.name + '/' + 'api' + '/' + this.microservicetype + '/' + id, {
        headers: {
          Authorization : this.auth()
    }
    });
  }

  insert(dto: DTO): Observable<any> {
      return this.http.post('http://localhost:' + this.port + '/' + this.name + '/' + 'api' + '/'  + this.microservicetype, dto, {
        headers: {
          Authorization : this.auth()
    }
    });
  }

  update(dto: DTO): Observable<DTO> {
      return this.http.put<DTO>('http://localhost:' + this.port + '/' + this.name + '/' + 'api' + '/'  + this.microservicetype, dto, {
        headers: {
          Authorization : this.auth()
    }
    });
  }

}