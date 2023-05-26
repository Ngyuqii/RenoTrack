export interface Unsplash {
    imageUrl: string;
    imageDes: string;
}

export interface User {
    userId: string;
    userName: string;
    userEmail: string;
    userPassword: string;
}

export interface Event {
    eventId: number;
    Subject: string;
    StartTime: Date;
    EndTime: Date;
    Description?: string;
    Location?: string;
    googleCalendarUrl?: string;
  }
