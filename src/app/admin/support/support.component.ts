import { Component, OnInit } from '@angular/core';
import {SupportService} from 'src/service/support.service';
import {SupportDTO} from 'src/dto/supportdto';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {

  supports: SupportDTO[];
  supporttoinsert: SupportDTO= new SupportDTO();

  constructor(private supportService: SupportService) { }

  ngOnInit() {

    this.getSupport();
  
  }
  getSupport(){
    this.supportService.getAll().subscribe(supports => this.supports=supports);

  }
  delete(support:SupportDTO){
    this.supportService.delete(support.id).subscribe(()=>this.getSupport());

  }
  update(support:SupportDTO){
    this.supportService.update(support).subscribe(()=> this.getSupport());

  }

  insert(support:SupportDTO){
    this.supportService.insert(support).subscribe(()=>this.getSupport())
  }

  clear(){
    this.supporttoinsert = new SupportDTO;
  }
}
