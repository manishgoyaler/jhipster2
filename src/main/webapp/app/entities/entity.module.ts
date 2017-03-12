import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Jhipster2AuthorModule } from './author/author.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        Jhipster2AuthorModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jhipster2EntityModule {}
