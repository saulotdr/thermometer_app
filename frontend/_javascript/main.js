$(document).ready(function () {
  var settingsHeroLastValue = sessionStorage.getItem('SettingsHero');
  $('#hero_id').toggle(settingsHeroLastValue);
});
$('#send_settings').click(function (e) {
  $('#send_settings').addClass('is-loading');
  let boiling = $('#boiling_input').val();
  let city = $('#city_input').val();
  let freezing = $('#freezing_input').val();

  if (city == null || city == "") {
    console.log('City is not a string');
    $('#notification_error_city').removeClass('is-hidden');
    $('#send_settings').removeClass('is-loading');
    return;
  }

  if (boiling == null || boiling === "" || freezing == null || freezing === "" || isNaN(boiling) || isNaN(freezing)) {
    console.log('boiling or freezing is not a number');
    $('#send_settings').removeClass('is-loading');
    $('#notification_error_thresholds').removeClass('is-hidden');
    return;
  }
  let body = {
    'city': city,
    'boiling_threshold': parseFloat(boiling),
    'freezing_threshold': parseFloat(freezing)
  }
  $.ajax({
    url: 'http://localhost:8080/v1/settings',
    method: 'PUT',
    data: JSON.stringify(body),
    contentType: 'application/json',
    crossDomain: true,
    success: function (response) {
      $('#send_settings').removeClass('is-loading');
      $('#notification_success').removeClass('is-hidden');
      $('#notification_success text').text('System settings successfully updated');
      sessionStorage.setItem('SettingsHero', 'is-hidden')

    },
    error: function (response) {
      $('#notification_error_city').removeClass('is-hidden');
      $('#notification_error_city text').text(`Error saving settings. ${response.responseText}.`);
      $('#send_settings').removeClass('is-loading');
    }
  });
})

$('.notification .delete').click(function (e) {
  $(this).parent().addClass('is-hidden');
});

$('#settings_button').click(function (e) {
  $('#hero_id').toggle('is-hidden');
});

$('#refresh_temperature').click(function (e) {
  refreshTemperature();
});

function refreshTemperature() {
  $('#refresh_temperature').addClass('is-loading');
  $.ajax({
    url: 'http://localhost:8080/v1/temperature/',
    method: 'GET',
    contentType: 'application/json',
    crossDomain: true,
    success: function (response) {
      $('#refresh_temperature').removeClass('is-loading');
      $('#content_body').text(`${response.city}. Last updated at: ${response.created_at} (UTC)`)
      $('#temperature').text(`${response.temperature} °C`);

    },
    error: function (response) {
      $('#notification_error_city').removeClass('is-hidden');
      $('#notification_error_city text').text(`Error refreshing stats. ${response.responseText}.`);
      $('#refresh_temperature').removeClass('is-loading');
    }
  });
}

var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function connect() {
  var socket = new SockJS('http://localhost:8080/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/freezing_threshold', function (message) {
      showFreezingNotification(message.body);
    });

    stompClient.subscribe('/topic/boiling_threshold', function (message) {
      showBoilingNotification(message.body);
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/hello", {}, JSON.stringify({ 'name': $("#name").val() }));
}

function showBoilingNotification(message) {
  $("#notification_is_boiling").removeClass('is-hidden');
  $("#notification_is_boiling text").text("🥵 The boiling temperature thereshold has reached. " + message);
}

function showFreezingNotification(message) {
  $("#notification_is_freezing").removeClass('is-hidden');
  $("#notification_is_freezing text").text("🥶 The freezing temperature thereshold has reached. " + message);
}

refreshTemperature();
connect();