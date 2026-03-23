import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Grupo } from 'src/app/model/grupo';
import { IdentificarGrupoPipe } from 'src/app/pipes/identificar-grupo.pipe';
import { GrupoService } from '../../services/grupo.service';

@Component({
  selector: 'app-grupo-nuevo',
  templateUrl: './grupo-nuevo.component.html',
  styleUrls: ['./grupo-nuevo.component.css'],
})
export class GrupoNuevoComponent implements OnInit {

  mostrar: boolean = false;
  nombre: string = '';
  miembrosText: string = '';

  @Output() readonly guardadoEvent = new EventEmitter<void>();

  constructor(
    private grupoService: GrupoService,
    private messageService: MessageService,
    private identificarGrupo: IdentificarGrupoPipe) {

  }

  ngOnInit(): void {

  }

  get miembros(): string[] {
    return this.miembrosText.split('\n').map(s => s.trim()).filter(s => s.length > 0);
  }

  iniciar(): void {

    this.nombre = '';
    this.miembrosText = '';
    this.mostrar = true;
  }

  guardar(): void {

    this.grupoService.crear(this.nombre, this.miembros).subscribe(
      grupo => this.guardadoExitoso(grupo),
      error => this.guardadoFallido(error)
    );
  }

  cancelar(): void {

    this.mostrar = false;
  }

  private guardadoExitoso(grupo: Grupo): void {

    this.messageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: `Grupo '${this.identificarGrupo.transform(grupo)}' creado`,
    });

    this.guardadoEvent.emit();
    this.mostrar = false;
  }

  private guardadoFallido(error: any): void {

    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: error.mensaje,
    });
  }
}
