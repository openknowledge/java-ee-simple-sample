/**
 * @author Sven Koelpin
 */
(function (w) {
    if (w.jsf) {
        jsf.ajax.addOnEvent(function (data) {
            if (data.status === 'begin') {
                document.getElementById('loader').style.display = 'block';
            }
            if (data.status === 'success') {
                document.getElementById('loader').style.display = 'none';
            }
        });
    }
})(window);