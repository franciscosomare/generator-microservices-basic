module.exports = {
    prompting
};

function prompting() {
    const log = console.log;

    const done = this.async();

    const prompts = [
        {
            type: 'string',
            name: 'appName',
            validate: input =>
                /^([a-z_][a-z0-9_]*)$/.test(input)
                    ? true
                    : 'Nombre de aplicacion invalido',
            message: 'Cual es el nombre de la aplicacion?',
            default: 'mi_servicio'
        },
        {
            type: 'string',
            name: 'packageName',
            validate: input =>
                /^([a-z_]{1}[a-z0-9_]*(\.[a-z_]{1}[a-z0-9_]*)*)$/.test(input)
                    ? true
                    : 'Nombre de package invalido',
            message: 'Cual es el su package name?',
            default: 'pe.financieraoh'
        },
        {
            type: 'list',
            name: 'proyectoTipo',
            message: 'Que tipo de Proyecto desea realizar?',
            choices: [
                {
                    value: 'core',
                    name: 'Core'
                },
                {
                    value: 'gateway',
                    name: 'Gateway'
                },
                {
                    value: 'rules',
                    name: 'Rules'
                },
                {
                    value: 'bss',
                    name: 'Bss'
                }                
            ],
            default: 'core'
        },
        {
            type: 'confirm',
            name: 'sql',
            message: 'Desea usar JPA?',
            default: true
        },
        {
            when: response => response.sql === true,
            type: 'list',
            name: 'databaseType',
            message: 'Que tipo de Base de Datos quiere utilizar?',
            choices: [
                {
                    value: 'postgresql',
                    name: 'Postgresql'
                },
                {
                    value: 'mysql',
                    name: 'MySQL'
                },
                {
                    value: 'mariadb',
                    name: 'MariaDB'
                }
            ],
            default: 'postgresql'
        },
        {
            type: 'list',
            name: 'buildTool',
            message: 'Que herramienta de build quiere utilizar?',
            choices: [
                {
                    value: 'maven',
                    name: 'Maven'
                },
                {
                    value: 'gradle',
                    name: 'Gradle'
                }
            ],
            default: 'maven'
        }
    ];

    this.prompt(prompts).then(answers => {
        Object.assign(this.configOptions, answers);
        
        // Definicion de prefijo y sigla de microservicio
        var proyecto, sigla; 
        switch (this.configOptions.proyectoTipo) {
            case 'core':
                proyecto = 'co';
                sigla = 'c';
                break;            
            case 'rules':
                proyecto = 'ru';
                sigla = 'r';
                break;
            case 'bss':
                proyecto = 'bs';
                sigla = 'b';
                break;
            case 'gateway':
                proyecto = 'gw';
                sigla = 'g';
                break;
            default:
                proyecto = 'co';
                sigla = 'c';
                break;
          }

        

        //Definicion del Paquete
        this.configOptions.packageName = this.configOptions.packageName +'.' 
                                        + this.configOptions.proyectoTipo  +'.'
                                        + proyecto + this.configOptions.appName;
        
                                        //Definicion del nombre de la Aplicacion
        this.configOptions.appName = 'ms' + sigla + '-' + this.configOptions.appName;
        
        //Definicion del Folder
        this.configOptions.packageFolder = this.configOptions.packageName.replace(/\./g, '/');
        if(this.configOptions.sql === false) {
            this.configOptions.databaseType = 'none';
            this.configOptions.dbMigrationTool = 'none';
        }
        const features = this.configOptions.features || [];
        this.configOptions.distTracing = features.includes('distTracing');
        this.configOptions.eurekaClient = features.includes('eurekaClient');
        this.configOptions.configClient = features.includes('configClient');
        done();
    });
}
