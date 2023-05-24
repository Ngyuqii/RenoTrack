import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { GoogleMapsService } from 'src/app/services/google-maps.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})

export class MapComponent implements OnInit {

  @ViewChild('mapContainer', {static: false}) gmap!: ElementRef;
  map!: google.maps.Map;

  constructor(private googleMapsService: GoogleMapsService) { }

  //Initialize the Google Maps instance.
  ngOnInit(): void {
    this.googleMapsService.load().then(() => {
      this.mapInitializer();
    });
  }

  //Set latitude and longitude coordinates
  //Set maps zoom size
  mapInitializer(): void {
    const coordinates = new google.maps.LatLng(1.327343, 103.890665);
    const mapOptions: google.maps.MapOptions = {
      center: coordinates,
      zoom: 15
    };

    this.map = new google.maps.Map(this.gmap.nativeElement, mapOptions);

    //Marker details
    const marker = new google.maps.Marker({
      position: coordinates,
      map: this.map,
      title: 'RenoTrack'
    });
  }

}
