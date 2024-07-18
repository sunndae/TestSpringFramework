// Asegurarse de que el documento esté completamente cargado antes de añadir manejadores de eventos
document.addEventListener('DOMContentLoaded', () => {
    // Manejador para botones de editar
    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();  // Prevenir la acción por defecto del botón
            const personaId = this.getAttribute('data-id');  // Obtener el ID almacenado en el atributo data-id
            console.log('Editar persona con ID:', personaId);
            // Redirigir a una página de edición, ajustar URL según la configuración del servidor
            window.location.href = `/editar/${personaId}`;
        });
    });

    // Manejador para botones de eliminar
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();  // Prevenir la acción por defecto del botón
            const personaId = this.getAttribute('data-id');  // Obtener el ID almacenado en el atributo data-id
            // Confirmar antes de proceder con la eliminación
            if (confirm('¿Estás seguro de que deseas eliminar esta persona?')) {
                console.log('Eliminar persona con ID:', personaId);
                // Realizar una solicitud fetch para eliminar, ajustar URL según la configuración del servidor
                fetch(`/eliminar/${personaId}`, { method: 'DELETE' })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Failed to delete persona');
                        }
                        return response.json();  // Procesar la respuesta
                    })
                    .then(data => {
                        console.log('Persona eliminada:', data);
                        window.location.reload();  // Recargar la página para reflejar los cambios
                    })
                    .catch(error => console.error('Error:', error));
            }
        });
    });
});
