package com.pokeshop.ecommerce.util.enumerado;

import com.pokeshop.ecommerce.util.GenericError;

public enum EnumGeneralError implements GenericError {

    IN000000("GEN000000", "", EnumTypeException.I),
    AD000000("GEN000000", "", EnumTypeException.A),
    AD000001("GEN000001", "Id no Existe", EnumTypeException.A),
    ER000000("GEN000000", "", EnumTypeException.E),
    ER000001("GEN000001", "Error al zipiar", EnumTypeException.E),
    ER000002("GEN000002", "Error al instanciar DocumentBuilderFactory", EnumTypeException.E),
    ER000003("GEN000003", "Error al transformar el XML", EnumTypeException.E),
    ER000004("GEN000004", "Error al borrar carpeta", EnumTypeException.E),
    ER000005("GEN000005", "La variable de entorno 'BUCKET_TYPE' no tiene un valor permitido", EnumTypeException.E),
    ER000006("GEN000006", "Error al subir archivo en el bucket", EnumTypeException.E),
    ER000007("GEN000007", "Error al obtener archivo del bucket", EnumTypeException.E),
    ER000008("GEN000008", "No existe el archivo solicitado", EnumTypeException.E),
    ER000009("GEN000009", "No se pudo descargar el archivo solicitado", EnumTypeException.E),
    ER000010("GEN000010", "Error al crear carpeta en el bucket", EnumTypeException.E),
    ER000011("GEN000011", "Error al renombrar archivo en el bucket", EnumTypeException.E),
    ER000012("GEN000012", "Ya existe un archivo con el nuevo nombre indicado en el bucket", EnumTypeException.E),
    ER000013("GEN000013", "Archivo buscado no existe en el bucket", EnumTypeException.E),
    ER000014("GEN000014", "Error al eliminar archivo en el bucket", EnumTypeException.E),
    ER000015("GEN000015", "Error al verificar la existencia del bucket", EnumTypeException.E),
    ER000016("GEN000016", "Error al cambiar los permisos del archivo", EnumTypeException.E),
    ER000017("GEN000017", "Error al obtener la url del arhivo", EnumTypeException.E),
    ER000018("GEN000018", "Error al buscar el objeto", EnumTypeException.E),
    ER000019("GEN000019", "La variable de entorno 'MQ_TYPE' no tiene un valor permitido", EnumTypeException.E),
    ER000020("GEN000020", "Error al instanciar la conexión MQ", EnumTypeException.E),
    ER000021("GEN000021", "Error al conectar MQ", EnumTypeException.E),
    ER000022("GEN000022", "Error al suscribirse MQ", EnumTypeException.E),
    ER000023("GEN000023", "Error al publicar un mensaje MQ", EnumTypeException.E),
    ER000024("GEN000024", "Error al desconectarse de la conexión MQ", EnumTypeException.E),
    ER000025("GEN000025", "Error al cerrar la conexión MQ", EnumTypeException.E),
    ER000046("GEN000046", "Sesión Expirada", EnumTypeException.E);

    private final String codigo;
    private final String mensaje;
    private final EnumTypeException type;

    EnumGeneralError(String codigo, String mensaje, EnumTypeException type) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.type = type;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public EnumTypeException getType() {
        return this.type;
    }

}