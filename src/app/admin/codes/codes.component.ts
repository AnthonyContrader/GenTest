import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {CodesDTO} from '../../../dto/codesdto';
import {CodesService} from '../../../service/codes.service';


@Component({
  selector: 'app-codes',
  templateUrl: './codes.component.html',
  styleUrls: ['./codes.component.css']
})
export class CodesComponent implements OnInit {

  codess: CodesDTO[];
  codestoinsert: CodesDTO = new CodesDTO();
  @ViewChild('editor') editor;
  text = '';
  constructor(private service: CodesService,) { }

  ngAfterViewInit() {
    this.editor.setTheme('monokai');
    this.editor.setMode('java');
    this.editor.getEditor().setOptions({
      enableBasicAutocompletion: true
    });
  }
  ngOnInit() {
    this.getCodes();
  }

  getCodes(){
    this.service.getAll().subscribe(codess => this.codess = codess);
  }

  delete(codes: CodesDTO){
    this.service.delete(codes.id).subscribe(() => this.getCodes())
  }

  update(codes: CodesDTO){
    this.service.update(codes).subscribe(() => this.getCodes())
  }

  insertcodes(codes: CodesDTO){
    this.service.insertcodes(codes).subscribe(() => this.getCodes())
  }

  clear(){
    this.codestoinsert = new  CodesDTO();
  }
}
