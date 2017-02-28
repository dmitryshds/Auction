/**
 * Created by Dmitriy on 21.02.2017.
 */

var maxFileSize = 2 * 1024 * 1024;

            $(document).on("change", "#upload-image", function() {

                 var file = $(this)[0].files;

                if ( errorMsg = validateFile(file) ) {

                    $('#error-len').html(errorMsg).show();

                      return;
                    }

                $('#error-len').html(errorMsg).hide();

                var img2 = readURL(this);

                readURL(img2);
            });

            function readURL(input) {
                    if (input) {

                        var reader = new FileReader();

                        reader.onload = function (e) {

                            $('#disp-image')

                                .attr('src', e.target.result).show();

                            $('#rem-image').show();
                        };

                        reader.readAsDataURL(input.files[0]);
                    }
                }
            var validateFile = function(file)
                {
                    if ( file[0].size > maxFileSize ) {

                      return 'Image size more then 2Mb';

                      }
                };

                $(document).on("click", "#rem-image", function () {

                    $('#disp-image').hide();

                    $('#upload-image').val('');

                    $('#rem-image').hide();
                })
