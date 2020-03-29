import { Component, OnInit } from '@angular/core';
import {ProgettiService}from 'src/service/progetti.service';
import {ProgettiDTO}from 'src/dto/progettidto';
import {UserService}from 'src/service/user.service';
import {UserDTO}from 'src/dto/userdto';


@Component({
  selector: 'app-progetti',
  templateUrl: './progetti.component.html',
  styleUrls: ['./progetti.component.css'],
  providers: [ProgettiService, UserService]
})
export class ProgettiComponent implements OnInit {

  progettis: ProgettiDTO[];
  users: UserDTO[];
  progettitoinsert: ProgettiDTO = new ProgettiDTO();
  user: UserDTO;


  constructor(private userService: UserService, private progettiService: ProgettiService) { }

  ngOnInit() {

    this.getUsers();
    this.getProgetti();
  }


  getProgetti(){
    this.progettiService.getAll().subscribe(progettis => this.progettis = progettis);
}
  getUsers(){
  this.userService.getAll().subscribe(users => this.users = users);
}

delete(progetti:ProgettiDTO){
    this.progettiService.delete(progetti.id).subscribe(() =>this.getProgetti());
}
update(progetti:ProgettiDTO){
    this.progettiService.update(progetti).subscribe(() => this.getProgetti());
}
insert(progetti:ProgettiDTO){

    this.user = this.progettitoinsert.userDTO;
    progetti.userDTO = this.user;

    this.progettiService.insert(progetti).subscribe(() => this.getProgetti());
    this.clear;

}
clear(){
    this.progettitoinsert=new ProgettiDTO();
}

}
