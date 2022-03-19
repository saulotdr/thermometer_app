'use strict';

$('#send_settings').click(function (e) {
  $('#send_settings').addClass('is-loading');
  var boiling = $('#boiling_input').val();
  var city = $('#city_input').val();
  var freezing = $('#freezing_input').val();

  if (city == null || city == '') {
    console.log('City is not a string');
    $('#notification_error_city').removeClass('is-hidden');
    $('#send_settings').removeClass('is-loading');
  }

  if (isNaN(boiling) || isNaN(freezing)) {
    console.log('boiling or freezing is not a number');
    $('#send_settings').removeClass('is-loading');
    $('#notification_error_thresholds').removeClass('is-hidden');
    return;
  }
  var body = {
    'city': city,
    'boiling_threshold': parseFloat(boiling).toPrecision(2),
    'freezing_threshold': parseFloat(freezing).toPrecision(2)
  };
  $.ajax({
    url: 'https://localhost:8080/settings',
    method: 'POST',
    data: JSON.stringify(body),
    crossDomain: true,
    success: function success(response) {
      $('#send_settings').removeClass('is-loading');
      $('#notification_error_cities').removeClass('is-hidden');
      $('#notification_error_cities').text('System settings successfully updated');
    },
    error: function error(response) {
      $('#notification_error_cities').removeClass('is-hidden');
      $('#notification_error_cities').text('Error saving settings. Please try again.');
      $('#send_reset_email').removeClass('is-loading');
    },
    dataType: 'json',
    contentType: 'json'
  });
  console.log('Button clicked');
});

$('.notification .delete').click(function (e) {
  $(this).parent().addClass('is-hidden');
});